package smirnov.dmitrii.weatherkt.presentation.common.dialogs;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
public class ProgressDialogFragment extends DialogFragment {

    private static final String TAG = ProgressDialogFragment.class.getSimpleName();
    private static final String KEY_MESSAGE = "progress-message";

    public static void showDialog(@NonNull FragmentManager fm, @NonNull String message) {
        ProgressDialogFragment fmt = (ProgressDialogFragment) fm.findFragmentByTag(TAG);
        if (fmt != null) {
            return;
        }
        fmt = newInstance(message);
        fm.beginTransaction()
                .add(fmt, TAG)
                .commitNowAllowingStateLoss();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean dismissIfExist(@NonNull FragmentManager fm) {
        ProgressDialogFragment fmt = findFragmentByTag(fm, TAG);
        if (fmt != null) {
            fmt.dismiss();
            return true;
        }
        return false;
    }

    @NonNull
    private static ProgressDialogFragment newInstance(@NonNull String message) {
        ProgressDialogFragment fmt = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        fmt.setArguments(bundle);
        return fmt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getArguments().getString(KEY_MESSAGE));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(true);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        // removes the goBack shareIntent to avoid shown dialogs being dismissed.
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentByTag(@NonNull FragmentManager fm, @NonNull String tag) {
        return (T) fm.findFragmentByTag(tag);
    }
}